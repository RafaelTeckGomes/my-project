import boto3
from time import strftime
from datetime import timedelta,datetime
from boto3.dynamodb.conditions import Key, Attr

#This function will update a scheduled item.
def updateItem(id):
    nextTime = datetime.now() + timedelta(minutes = 15)
    print(nextTime.strftime("%Y%m%d%H%M%S"))
    schedule_date = nextTime.strftime("%Y%m%d%H%M%S")
    try:
        client = boto3.resource('dynamodb')
        table = client.Table("pipe_line_scheduler")
        table.update_item(
          Key = {
            'id': id
          },
          UpdateExpression="SET nextDateToRun = :dateTime, executed = :val",
          ExpressionAttributeValues={':dateTime': schedule_date, ':val':False}
        )
    except Exception as e:
           print("Exception during scheduling: {}".format(e))
