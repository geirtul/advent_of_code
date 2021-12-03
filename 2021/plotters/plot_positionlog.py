import numpy as np
import sys
import matplotlib.pyplot as plt
from matplotlib import animation

# Load the positionLog data
filenames = sys.argv[1:]
position_logs = [np.genfromtxt(filename, delimiter=",", dtype=int) for filename in filenames]



fig, ax = plt.subplots(len(position_logs), 1)
for i, data in enumerate(position_logs):
    ax[i].set_xlim(0, np.amax(data[:,0]))
    ax[i].set_ylim(0, np.amax(data[:,2]))
    #ax[i].set_title(f"Position Log")
    ax[i].set_xlabel("Horizontal position")
    ax[i].set_ylabel("Depth")

lines = []
for i, fname in enumerate(filenames):
    line, = ax[i].plot([], [], lw=2, label=fname.split('/')[-1])
    lines.append(line)

def init():
    for line in lines:
        line.set_data([], [])
    return lines


def animate(i):
    for j, data in enumerate(position_logs):
        x = data[:i,0]
        y = data[:i,2] # treat z as y in 2D plot
        lines[j].set_data(x, y)
    return lines


anim = animation.FuncAnimation(
    fig, animate, init_func=init, frames=position_logs[0].shape[0], blit=True,
)
for a in ax:
    a.invert_yaxis()

outfile = "../outputs/positionlog.gif"
anim.save(outfile, fps=30)
plt.show()


