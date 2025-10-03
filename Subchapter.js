class SubChapter extends TableOfContents {
  constructor(name) {
    super();
    this.name = name;
    this.elements = [];
  }

  addParagraph(text) {
    const Paragraph = require('./Paragraph');
    const p = new Paragraph(text);
    this.elements.push(p);
    return p;
  }

  addImage(imageName) {
    const Image = require('./Image');
    const i = new Image(imageName);
    this.elements.push(i);
    return i;
  }

  addTable(title) {
    const Table = require('./Table');
    const t = new Table(title);
    this.elements.push(t);
    return t;
  }

}
