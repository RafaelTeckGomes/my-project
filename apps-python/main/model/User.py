class User:
    
    def __init__(self,first_name,last_name,role):
        self.first_name = first_name
        self.last_name = last_name
        self.role = role
        
    def getFirstName(self):
        return self.first_name    
    
    def getLastName(self):
        return self.last_name   
     
    def getRole(self):
        return self.role    
    
    def __str__(self):
        return "first name: {0}, last name: {1}, role: {2}".format(self.first_name, self.last_name, self.role)