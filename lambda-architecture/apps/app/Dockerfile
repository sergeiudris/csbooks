FROM la.cljbase

# COPY .  /opt/code/app
COPY project.clj  /opt/code/app
RUN lein deps

COPY .  .

EXPOSE 35543
EXPOSE 7888
EXPOSE 8080
EXPOSE 8000

# CMD ["bash","c", "nrepl_clj"]
CMD ["bash","c", "main"]

