#coding:utf-8
import urllib2
import time
def url_user_agent(url):
        #设置使用代理
#     proxy = {'http':'36.32.21.146:8080'}
#     proxy_support = urllib2.ProxyHandler(proxy)
#     # opener = urllib2.build_opener(proxy_support,urllib2.HTTPHandler(debuglevel=1))
#     opener = urllib2.build_opener(proxy_support)
#     urllib2.install_opener(opener)
    i_headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.48'}
    req = urllib2.Request(url,headers=i_headers)
    html = urllib2.urlopen(req)
    if url == html.geturl():
        doc = html.read()
        return doc
    return
begin = 'http://www.stat-nba.com/game/'
end = '.html'
# l1 = [7451,10000]
# l2 = [10068,15000]
# l3 = [18863,20000]
#15062 15063 20056 20057
#1347-3725  20055
# leftfs = open('k:/left.txt','r')
# for line in leftfs.readlines():
for i in [37442,37444,37445,37446,37450,37454]:
    try:
        url = begin + str(i+1)+end
        doc = url_user_agent(url)
        out = open('K:/allData/'+str(i),'w')
        print doc
        out.write(doc)
        out.close()
        time.sleep(0.05)
        print i
    except Exception,e :
        print e
