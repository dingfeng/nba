import re
from matplotlib.finance import md5
data = 'Thu Feb 15 17:46:04'
patt = r'^(\w){3}'
md5 = re.compile(patt)
m =md5.match(data)
if m is not None:
    print m.group(2)