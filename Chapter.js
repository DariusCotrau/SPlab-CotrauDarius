class Chapter extends TableOfContents {
  constructor(name) {
    super();
    this.name = name;
    this.subChapters = [];
  }

  addSubChapter(name) {
    const SubChapter = require('./Subchapter');
    const sc = new SubChapter(name);
    this.subChapters.push(sc);
    return sc;
  }

}

