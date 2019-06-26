from flask import Flask,jsonify
from main.service import user_service   



app = Flask(__name__)

@app.route('/new-user', methods=['POST'])
def create_new_user():
    user = user_service.create_new_user()
    return jsonify(user.__str__())



@app.route('/get-user/<idUser>',methods=['GET'])
def getUser(idUser):
    user = user_service.get_user_by_Id(idUser)
    return jsonify(user.__str__())
    


if __name__ == '__main__':
    app.run()
