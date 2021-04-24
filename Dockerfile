FROM node:lts
COPY gate-simulator /node_js
WORKDIR /node_js
ENTRYPOINT ["npm", "start"]
EXPOSE 9999
