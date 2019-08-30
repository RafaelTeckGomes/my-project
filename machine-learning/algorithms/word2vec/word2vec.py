import gzip
import gensim
import logging
import os
from gensim.models import Word2Vec,KeyedVectors
import boto3
import pandas as pd
import io
import csv
import sys
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
import pickle


bucketName = 'bucket-test'

logging.basicConfig(
    format='%(asctime)s : %(levelname)s : %(message)s',
    level=logging.INFO)


def readFile(input_file):
    with open (input_file, "r") as myfile:
        for line in myfile:
            yield gensim.utils.simple_preprocess(line)

def saveModel(model):
    session = boto3.Session()
    s3 = session.resource('s3')
    obj = s3.Object(bucketName,'model.pkl')
    f = pickle.dumps(model)
    obj.put(Body=f)
    print 'model saved in the bucket: [ '+bucketName+' ]'

def saveFile(dataList):
    fileName = 'fields.source'
    mapping = [ ("[", ""), ("]", ""), ("'", ""), (","," ")]
    with open(fileName, 'w') as f:
        for item in dataList:
            value = str(dataList[item])
            for k, v in mapping:
                value = value.replace(k, v)
            f.write(value + '\n')


    client = boto3.client('s3')
    client.upload_file(fileName, bucketName, 'fields.source')


def transformFileData():
    s3 = boto3.resource('s3')
    bucket = s3.Bucket(bucketName)
    sourceFolder = "source/"
    listFiles = list()
    for obj in bucket.objects.filter(Prefix=sourceFolder):
            path, filename = os.path.split(obj.key)
            if ".csv" in filename:
                listFiles.append(filename)
    data = {}
    for fileNameOriginal in listFiles:
        print("Applying transformation on file: [ "+fileNameOriginal+" ]")
        destinationFile = fileNameOriginal+'_tmp'
        session = boto3.Session()
        s3 = session.resource('s3')
        s3.meta.client.download_file(bucketName,sourceFolder+fileNameOriginal, destinationFile)
        data_row = {}
        with open(destinationFile, 'r') as csvfile:
            spamreader = csv.reader(csvfile, delimiter=',', quotechar='"')
            isHeader = True
            for row in spamreader:
                if isHeader == False:
                    report_id = row[4]
                    value = row[19]
                    #print "Value: "+value
                    #data[report_id].append(value)
                isHeader = False

    #saveFile(data)
    print 'File saved in the bucket: [ '+bucketName+' ]'
    return  listFiles



def createInitialModel():
    destinationFile = 'fields.source'
    session = boto3.Session()
    s3 = session.resource('s3')
    s3.meta.client.download_file(bucketName, 'fields.source', destinationFile)
    documents = list(readFile(destinationFile))

    model = Word2Vec(
        documents,
        window=10,
        min_count=2,
        workers=10)
    saveModel(model)

def getModel():
    destinationFile = 'model.pkl'
    session = boto3.Session()
    s3 = session.resource('s3')
    s3.meta.client.download_file(bucketName, 'model.pkl', destinationFile)
    f = open("model.pkl", "rb")
    model = pickle.load(f)
    return model


def training():
    print "Training the model..."
    print "Building vocabulary..."
    model = getModel()
    destinationFile = "model.pkl"
    session = boto3.Session()
    s3 = session.resource('s3')
    s3.meta.client.download_file(bucketName, 'model.pkl', destinationFile)
    model.build_vocab(destinationFile, update=True)
    model.train(destinationFile, total_examples=model.corpus_count, epochs=model.iter)
    print 'Model trained'


def showResults(model):

    w1 = ["first_name"]
    resultList = list()
    result = model.wv.most_similar(positive=w1,topn=10)
    for x in result:
        resultList.append(x[0])

    print ""
    print "Next Suggestions based on [ "+w1[0]+" ]"
    print ""
    print resultList
    print "######################################################################################"

    w1 = ["home_address"]
    resultList = list()
    result = model.wv.most_similar(positive=w1,topn=10)
    for x in result:
        resultList.append(x[0])

    print ""
    print "Next Suggestions based on [ "+w1[0]+" ]"
    print ""
    print resultList
    print "######################################################################################"


    X = model[model.wv.vocab]
    pca = PCA(n_components=2)
    result = pca.fit_transform(X)
    plt.scatter(result[:, 0], result[:, 1])
    plt.figure(figsize=(12,10))
    words = list(model.wv.vocab)
    for i, word in enumerate(words):
        plt.annotate(word, xy=(result[i, 0], result[i, 1]))

    plt.show()




def init():
    modelFile = 'model.pkl'
    session = boto3.Session()
    s3 = session.resource('s3')
    s3.meta.client.download_file(bucketName, modelFile, modelFile)
    f = open("model.pkl", "rb")
    model = pickle.load(f)
    print("Done reading data file")
    #model = gensim.models.KeyedVectors.load_word2vec_format(destinationFile,binary=True)
    #model = KeyedVectors.load(path, mmap='r')
    showResults(model)



if __name__ == '__main__':
    transformFileData()
    createInitialModel()
    training()
    init()
