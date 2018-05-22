FROM eclipse-mosquitto:1.4.12

COPY rdsh-docker-injected-mosquitto-entrypoint.sh /entrypoint.sh
COPY rdsh-docker-injected-mosquitto.conf /mosquitto/config/mosquitto.conf
COPY rdsh-docker-injected-mosquitto.passwd /etc/mosquitto/passwd
ENTRYPOINT ["sh", "/entrypoint.sh"]
CMD tail -f /dev/null