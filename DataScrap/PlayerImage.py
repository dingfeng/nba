#coding:utf-8
import m
src = "http://www.stat-nba.com/image/playerImage/"
print 'sdf'
for i in range(4362):
  print str(i)
  try:
    file = open('F:/playerImage/'+'175'+'.jpg','w')
    url = src + '195'+'.jpg'
    image = m.url_user_agent(url)
    file.write(str)
    file.close()
  except Exception,e:
    print 'he'