#coding:utf-8
import urllib2
import time
def url_user_agent(url):
    i_headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.48'}
    req = urllib2.Request(url,headers=i_headers)
    html = urllib2.urlopen(req)
    if url == html.geturl():
        doc = html.read()
        return doc
    return
# print url_user_agent("http://china.nba.com/wap/static/data/scores/daily_2014-12-03.json")
# print url_user_agent("http://www.basketball-reference.com/boxscores/198410270DAL.html")
urlbase = "http://www.basketball-reference.com/leagues/"
filename = "k:/basketballR/"
for i in range(1954,1986):
    if i < 1950:
        url = urlbase + "BAA_"+str(i)+"_games.html"
    else :
        url = urlbase + "NBA_"+str(i)+"_games.html"
    xml = url_user_agent(url)
    file = open(filename+str(i),'w')
    print url
    file.write(xml)
    file.close()
    time.sleep(0.5)