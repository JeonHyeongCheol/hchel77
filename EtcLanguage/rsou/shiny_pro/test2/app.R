library(shiny)

# Define UI for application that draws a histogram
ui <- fluidPage(
  textInput('myText','텍스트입력'), # 텍스트 창 만듬.
  verbatimTextOutput('txt1'), # id를 준 것. 입력 값을 감지함.
  
  sliderInput('numeric', '숫자입력', 10, min = 1, max = 100, step = 2), # 바를 보여줌.
  verbatimTextOutput('txt2'), # 바에서 움직이는 값을 감지함.
  
  selectInput('sel', '선택', choic = c('초'='새내기', '중', '고')), # 콤보박스, JSP Select랑 똑같음.
  verbatimTextOutput('txt3'),
  
  actionButton("action", label = 'Action'), # 버튼 만들기.
  hr(), # 줄 긋기
  verbatimTextOutput('value1'), # 버튼누른 값을 가져옴.
  
  radioButtons("radio", label = h3("Radio buttons"), choices = list("Choice1" = 1, "Choice2" = 2, "Choice3" = 3 )), # 라디오버튼
  verbatimTextOutput('value2') # 라디오 버튼 값 가져옴
)

# Define server logic required to draw a histogram
server <- function(input, output) {
  output$txt1 <- renderText({input$myText}) # MyText(input)에서 들어오는 값을 txt1(output)에 출력해줌.
  output$txt2 <- renderText({input$numeric}) # 바(numeric)에서 움직이는 값을 txt2(ouptut)에 출력해줌.
  output$txt3 <- renderText({input$sel}) # 선택된 값(sel)을 가져와 txt3(output)에 출력해줌.
  output$value1 <- renderText({input$action}) # 버튼 누른 횟수 출력 해줌(Value1). 
  output$value2 <- renderText({input$radio}) # 라디오에서 출력된 값을 보내줌.
}

# Run the application 
shinyApp(ui = ui, server = server)

