import requests, time, json

phone = '28601404'
phone_code = '371'

#print('trying to send')
#sendData = {'login': "+%s%s" % (phone_code, phone), 'confirmation_type': "call_code"}
#response = requests.get("https://api.tokentransit.com/v1/user/login?env=live&phone_number=%2B1%20" + '%s%s' % (phone_code, phone))
#print(response.status_code)
#print(response.text)

print('trying to send')
sendData = {'login': "+%s%s" % (phone_code, phone), 'confirmation_type': "message_code"} # "call_code" works as well
response = requests.post('https://b.utair.ru/api/v1/login/', data=sendData)
if response.status_code != 200:
    print(response.text)