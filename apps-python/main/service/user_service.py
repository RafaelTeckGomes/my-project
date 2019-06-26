from main.util.user_factory import factory


def create_new_user():
    f = factory()
    return f.createNewUserFake(1)


def get_user_by_Id(idUser):
    f = factory()
    try:
        user = f.createNewUserFake(int(idUser))
        return user
    except Exception as e:
        return getattr(e, 'message', str(e))    