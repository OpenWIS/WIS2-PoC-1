## Important Information
It is possible that when running docker within a corporate network, connection to the internet from within the container cannot be established.
In short, this is due to a DNS resolution error.

To resolve this problem:
1. Discover the **local** IPs of your DNS server(s)
2. Create the `/etc/docker/daemon.json` file (or update it if it already exists) and enter the following information:
```
{
	"dns": ["xxx.xxx.xxx.xxx","yyy.yyy.yyy.yyy"]
}
```
3. Restart the Docker service, e.g.:
```
sudo systemctl restart docker.service
```