FROM node:16-alpine

WORKDIR /appfront

ENV PATH /app/node_modules/.bin:$PATH

COPY ./package.json ./

RUN npm install 

RUN npm install -g @vue/cli

COPY ./ ./

EXPOSE 8080

CMD ["npm", "run", "serve"]