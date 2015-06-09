#coding:utf-8
import urllib2

def url_user_agent(url):
    i_headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.48'}
    req = urllib2.Request(url,headers=i_headers)
    html = urllib2.urlopen(req)
    if url == html.geturl():
        doc = html.read()
        return doc
    return
begin = 'http://www.stat-nba.com/player/'
end = '.html'
i = 1825
while i < 5000:
 try:
     url = begin + str(i+1)+end
     doc = url_user_agent(url)
     out = open('K:/allPlayer/'+str(i),'w')
     out.write(doc)
     out.close()
    
 except Exception,e:
    print str(e)
 finally:
      i += 1