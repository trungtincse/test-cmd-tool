const fs = require('fs');
const path = require('path');

const views = [];

fs.readdirSync(path.join(__dirname, './view')).forEach((filename) => {
  const filepath = path.join(__dirname, './view', filename);
  const filestat = fs.lstatSync(filepath);
  if (filestat.isDirectory()) {
    const dirname = filename;
    fs.readdirSync(filepath).forEach((filename) => {
      views.push({
        template: `./view/${dirname}/${filename}`,
        filename: `${dirname}/${filename}`
      });
    });
  } else {
    views.push({
      template: `./view/${filename}`,
      filename
    });
  }
});

module.exports = {
  views
};
