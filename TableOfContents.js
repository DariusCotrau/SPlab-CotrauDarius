class TableOfContents {
  constructor() {
    this.entries = [];
  }

  addEntry(title, page = null) {
    this.entries.push({ title, page });
  }

  // Print simplificat și moștenit de toate clasele
  print() {
    const kind = this.constructor.name;
    const label = this.name || this.title || this.text || this.imageName || '';
    console.log(label ? `${kind}: ${label}` : kind);
  }
}
