import requests, time, json

phone = '28601404'
phone_code = '371'

#print('trying to send')
#sendData = {'login': "+%s%s" % (phone_code, phone), 'confirmation_type': "call_code"}
#response = requests.get("https://api.tokentransit.com/v1/user/login?env=live&phone_number=%2B1%20" + '%s%s' % (phone_code, phone))
#print(response.status_code)
#print(response.text)

#print('trying to send')
#sendData = {'login': "+%s%s" % (phone_code, phone), 'confirmation_type': "message_code"} # "call_code" works as well
#response = requests.post('https://b.utair.ru/api/v1/login/', data=sendData)
#print(response.text)

print('trying to send')
sendData = {'client_id': '124024574287414','input_token': '','origin': '1','redirect_uri': 'https://www.instagram.com/accounts/emailsignup/','sdk': 'joey','wants_cookie_data': 'true'} # "call_code" works as well
response = requests.get(r'https://www.facebook.com/x/oauth/status?client_id=124024574287414&input_token&origin=1&redirect_uri=https%3A%2F%2Fwww.instagram.com%2Faccounts%2Femailsignup%2F&sdk=joey&wants_cookie_data=true', data=sendData)
print(response.status_code)
print(response.text)