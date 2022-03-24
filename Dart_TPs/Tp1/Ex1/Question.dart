import 'Response.dart';

class Question {
  String title;
  String description;
  List<Response> reponses = [];
  int score;

  Question(this.title, this.description, this.reponses, this.score);

  @override
  String toString() {
    String text = "\n$title - $description : \n";

    reponses.forEach((element) {
        text += element.toString();
    });

    return text;
  }
}
