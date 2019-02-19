import docker
client = docker.from_env()

result = client.containers.run("python-hello", ["echo", "hello", "world"])
print result

filename = "result.txt"
f = open(filename,'w+')
print >>f, result

# print "container list : "
# for container in client.containers.list():
#      print container.id
