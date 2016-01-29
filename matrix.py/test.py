class check_size:
    def __init__(self, x):
        self.x = x
    def get(self):
        return self.x
    def put(self,value):
        self.x=value

if __name__ == "__main__":

    t1= check_size(10)
    print t1.get()
    t1.put(4)
    print t1.get()
