FROM node:16-alpine

WORKDIR /appfront

RUN npm install -g npm

RUN npm install -g @vue/cli

# RUN npx vue-cli-service build

COPY ./package.json ./

RUN npm install 

COPY ./ ./

RUN rm -rf node_modules package-lock.json

ENV PATH="./frontend/node_modules/.bin:$PATH"

# RUN npm run build

EXPOSE 8080

CMD ["npm", "run", "serve"]