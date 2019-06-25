from flask import Flask,jsonify
from main.service.user_service import getUserById   



app = Flask(__name__)

@app.route('/')
def create_new_user():
    user = getUserById(123)
    return jsonify(user.__str__())



if __name__ == '__main__':
    app.run()
