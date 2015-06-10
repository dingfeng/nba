import numpy as np
import math
import matplotlib.pyplot as plt

def getSuppose(data):
	tDis = np.array([6.3138, 2.92, 2.3534, 2.1318, 2.015, 1.9432, 1.8946, 1.8595, 1.8331, 1.8125])
	length = len(data)
	tValue = tDis[length - 1]
	mean = np.mean(data)
	var = 0
	for number in data:
		var = var + (number - mean) * (number - mean)
	var = var/(length - 1)
	Std = math.sqrt(var)
	
	range = Std/math.sqrt(length) * tValue
	
	return mean, range

def getData():
	file_object = open("D:/dataToPL.txt", 'r')
	try:
		filename = file_object.readline().strip('\n') 
		playername = file_object.readline()
		line = file_object.readline();
		#Match Number
		Number = int(line);
		#Get lables
		line = file_object.readline()
		date = line.split(',')
		#Get match data
		data = np.zeros((5, Number + 1));
		rangeDone = np.zeros((5, Number + 1));
		maxi = 0;
		for i in range(5):
			line = file_object.readline()
			parts = line.split(',')
			dataSub = [float(P) for P in parts]
			maxNo = np.max(dataSub)
			maxi = maxNo if (maxNo > maxi) else maxi
			rangeSub = np.zeros(Number + 1);
			mid, rangeSub[Number] = getSuppose(dataSub)
			dataSubDone = np.zeros(Number + 1);
			for j in range(Number):
				dataSubDone[j] = dataSub[j]
			dataSubDone[Number] = mid
			data[i] = dataSubDone
			rangeDone[i] = rangeSub
	finally:
		file_object.close()
		
	return filename, playername, date, data, rangeDone, Number, maxi

if __name__ == '__main__':
	filename, playername, date, data, rangeDone, number, maxi = getData()
	colors = np.array([(0.988, 0.4196, 0.478), (0.549, 0.914, 0.494), (0.839, 0.514, 0.894), 
					(0.984, 0.737, 0.518), (0.12568, 0.6549, 0.9019)])
	x = np.arange(1, number + 2, 1)
	
	fig = plt.figure()
	ax = fig.add_subplot(111)
	
	for d, ranged, co in zip(data, rangeDone, colors):
		ax.errorbar(x, d, yerr = ranged, color = co, marker = '.', mfc = co,
                     mec = (0.8078, 0.8078, 0.8078, 1), ms = 20, mew = 3, linestyle = '--')
		
	ax.grid(True)
	ax.set_yticks(np.linspace(0, maxi + 2, 8))
	plt.xticks(x, date)
	ax.legend(('PTS', 'REB', 'AST', 'FT%', '3PT%'), fontsize = 10)
	#plt.xticks(x, people)
	
	
	plt.savefig(filename, dpi=70, facecolor='w', edgecolor='w',
        orientation='portrait', papertype=None, format=None,
        transparent=True, bbox_inches=None, pad_inches=0.1,
        frameon=None)
