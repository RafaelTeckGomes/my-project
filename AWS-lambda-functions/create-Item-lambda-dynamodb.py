import boto3
from time import strftime
from datetime import timedelta,datetime

#This function is a simple demonstration how to call a dynamoDB from lambda creating a item on db
def createItem(id):
    # this will create dynamodb resource object
    client = boto3.resource('dynamodb')
    #calling a table on dynamoDB
    table = client.Table("pipe_line_scheduler")
    nextTime = datetime.now() + timedelta(minutes = 15)
    print(nextTime.strftime("%Y%m%d%H%M%S"))
    schedule_date = nextTime.strftime("%Y%m%d%H%M%S")
    table.put_item(Item = {
        'id': id,
        'nextDateToRun': schedule_date,
        'executed': False
      })
