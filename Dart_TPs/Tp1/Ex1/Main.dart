import 'Question.dart';
import 'Questionnaire.dart';
import 'Response.dart';
import 'dart:io';
import 'dart:convert';

void main() {
  List<Question> questions = [
    new Question(
        "Q1",
        "What does HTML stand for?",
        [
          new Response("Hyper Trainer Marking Language", false),
          new Response("Hyper Text Markup Language", true),
          new Response("Hyper Text Marketing Language", false)
        ],
        2),
    new Question(
        "Q2",
        "Which of the following is the correct way of making a string in Java?",
        [
          new Response("String \"Text\";", false),
          new Response("String text = 'text';", false),
          new Response("String text = \"text\";", true)
        ],
        3),
    new Question(
        "Q3",
        "Which of the following is the correct way to use the standard namespace in C++?",
        [
          new Response("Using namespace std;", true),
          new Response("Using namespace standard;", false),
          new Response("Using standard namespace;", false)
        ],
        4)
  ];

  var questionnaire = new Questionnaire("Quiz", questions);

  int score = 0;
  print("--------------------------------------");
  print("${questionnaire.title} : ");
  questionnaire.questions.forEach((question) {
    print("\t ${question.title} - ${question.description}");
    var i = 1;
    question.reponses.forEach((response) {
      print("\t\t $i - ${response.title}");
      i++;
    });
    print("reponse : ");
    var r;
    do {
      r = stdin.readLineSync(encoding: utf8);
      r = int.parse(r);
    } while (r <= 0 || r > 3);

    if (question.reponses[r - 1].correct) {
      score += question.score;
    }
  });
  print("\t\t\t-----------------");
  print("\t\t\tYour score is : $score");
  print("\t\t\t-----------------");
  print(
      "\n--------------------------------------------------------------------\n");
  print("Answers Are : ");
  questionnaire.questions.forEach((q) {
    print(q);
  });
}
