import requests
url = '/exec/subcmd'
payload = "{\n    \"cmd\": 9999,\n    \"uid\": 211076676,\n    \"subcmd\": 0,\n    \"params\": [\n        {\n            \"type\": \"int\",\n            \"value\": 92153100\n        }\n    ]\n}"
headers = {
  'Content-Type': 'application/json'
}
response = requests.request('POST', url, headers = headers, data = payload, allow_redirects=False, timeout=undefined, allow_redirects=false)
print(response.text)