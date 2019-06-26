class User:
    
    def __init__(self,first_name,last_name,role,id_user):
        self.first_name = first_name
        self.last_name = last_name
        self.role = role
        self.id_user = id_user
        
    def getFirstName(self):
        return self.first_name    
    
    def getLastName(self):
        return self.last_name   
     
    def getRole(self):
        return self.role
    
        
    def getId(self):
        return self.id
    
    def __str__(self):
        return "first name: {0}, last name: {1}, role: {2}, id: {3}".format(self.first_name, self.last_name, self.role, self.id_user)