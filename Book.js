class Book extends TableOfContents {
  constructor(title) {
    super();
    this.title = title;
    this.authors = [];
    this.chapters = [];
    this.tableOfContents = new TableOfContents();
  }

  addAuthor(authorOrName) {
    const author =
      typeof authorOrName === 'string' ? new Author(authorOrName) : authorOrName;
    this.authors.push(author);
    return author;
  }

  addChapter(name) {
    const chapter = new Chapter(name);
    this.chapters.push(chapter);
    this.tableOfContents.addEntry(name);
    return chapter;
  }

  getChapter(index) {
    return this.chapters[index];
  }

}

