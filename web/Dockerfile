FROM node:16-alpine

WORKDIR /appfront

ENV PATH /app/node_modules/.bin:$PATH

COPY ./package.json ./

RUN npm install -g npm

RUN npm install 

COPY ./ ./

EXPOSE 8080

CMD ["npm", "run", "serve"]