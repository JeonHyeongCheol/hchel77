#install.packages("shinyjs")
#install.packages("shinycssloaders")
library(shiny)
library(shinyjs)
library(shinycssloaders)
# Define UI for application that draws a histogram
ui <- fluidPage(
  inlineCSS(
    "
    #kbs{font-size:30px; font-family:'궁서'}
    "
  ),
  includeCSS("www/style.css"), # 외부에 작성한 css 파일 연동
  titlePanel("간단한 계산기"),
  wellPanel(p("숫자 입력 후 연산자 선택"), class = 'mbc', id = 'kbs'),
  textInput("num1", "숫자1"),  
  textInput("num2", "숫자2"),
  radioButtons("op", "연산자 선택", c('+'='가','-'='감','*'='승','/'='제'), inline = T),
  h2("연산 결과는 "),
  textOutput('result')
)

# Define server logic required to draw a histogram
server <- function(input, output) {
   output$result <- renderText({
     req(input$num1, input$num2) # req 는 jsp request랑 같은 것.
     num1 <- as.numeric(input$num1)
     num2 <- as.numeric(input$num2)
     # print(num1) # console로 출력
     
     tryCatch({
        if(input$op == '가') {
           paste(num1, '+', num2, '=', num1 + num2) # paste : 더하기
        } else if(input$op == '감') {
          paste(num1, '-', num2, '=', num1 - num2)
        } else if(input$op == '승') {
          paste(num1, '*', num2, '=', num1 * num2)
        } else if(input$op == '제') {
          paste(num1, '/', num2, '=', round(num1 / num2, 2))
        }
     }, error = function(e) cat(e$message))
   })
}

# Run the application 
shinyApp(ui = ui, server = server)

