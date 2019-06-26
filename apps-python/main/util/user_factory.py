from main.model.User import User 

class factory:
    
    def __init__(self):
        print("in init")
    
    def createNewUserFake(self,idUser):       
        if idUser == 1:
            return User("JOHN","LAVOYER","MANAGER",idUser)
        elif idUser == 2:
            return User("MARCUS","LEMOES","TESTER",idUser)        
        elif idUser == 3:
            return User("RICHARD","LAVOYER","DEVELOPER",idUser)        
        else: 
            raise Exception("User not found!")