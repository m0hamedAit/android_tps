import 'Question.dart';


class Questionnaire{
  String title;
  List<Question> questions = [];
  
  Questionnaire(this.title, this.questions);
  
  @override
  String toString(){
    String text = "$title : \n";
    
    questions.forEach((element){
        text += element.toString();
    });
    
    return text; 
  }
}