import "Category.dart";

class Ordinateur {
  String name;
  String mark;
  double price;
  String description;
  int stock;

  Category categorie;

  Ordinateur(this.name, this.mark, this.price, this.description, this.stock,
      this.categorie);

  double calculPrice(int quantity) => price * quantity;

  @override
  String toString() {
    
    return '''
          {
            name : $name
            mark : $mark
            price : $price \$
            description : $description
            stock : $stock
          }
          ''';
  }
}
