s = """curl "http://1d3p.wp.codeforces.com/new" -H "Connection: keep-alive" -H "Cache-Control: max-age=0" -H "Origin: http://1d3p.wp.codeforces.com" -H "Upgrade-Insecure-Requests: 1" -H "DNT: 1" -H "Content-Type: application/x-www-form-urlencoded" -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36" -H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8" -H "Referer: http://1d3p.wp.codeforces.com/" -H "Accept-Encoding: gzip, deflate" -H "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7" -H "Cookie: __utmc=71512449; _ym_uid=1531065713896913846; _ym_d=1531065713; __utma=71512449.1117333400.1531065712.1531943538.1536914785.4; __utmz=71512449.1536914785.4.2.utmcsr=assets.codeforces.com^|utmccn=(referral)^|utmcmd=referral^|utmcct=/files/wp2018/; evercookie_png=m83bvq4gvpotqymjw9; evercookie_etag=m83bvq4gvpotqymjw9; evercookie_cache=m83bvq4gvpotqymjw9; 70a7c28f3de=m83bvq4gvpotqymjw9; _ym_isad=1; __utmt=1; __utmb=71512449.57.9.1536916947769; JSESSIONID=9E0EBEBE8E92E7A1DA9D31B480F9AA35" --data "_af=34be50b38beccce4^&proof=9^&amount=3^&submit=Submit" """ 
begin = 0
for i in range(len(s) - 7):
	if s[i:i + 7] == "amount=":
		begin = i + 7
		break                                                 
#proof=9

begin2 = 0
for i in range(len(s) - 6):
	if s[i:i + 6] == "proof=":
		begin2 = i + 6
		break                                                 

fout = open('text.txt', 'w')
for i in range(4, 101):
	new_s = str(s[:begin2] + str(i * i) + s[begin2 + 1:begin]) + str(i) + s[begin + 1:]
	fout.write(new_s + '\n')

fout.close()
	