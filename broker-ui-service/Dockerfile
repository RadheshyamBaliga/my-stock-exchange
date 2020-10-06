FROM node:alpine
WORKDIR /usr/src/mse-broker-ui-service
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD [ "npm", "start"]