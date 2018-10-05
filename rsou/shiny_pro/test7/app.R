# 자바스크립트(Javascript Event)
library(shiny)
library(shinyjs)
#install.packages("V8")
library(V8)
library(shinycssloaders)

# js 함수를 샤아니를 통해 기억
jsCode <- "shinyjs.pageCol = function(params){
  $('body').css('background', params);
}" # javascript 함수

# Define UI for application that draws a histogram
shinyApp( # 이런식으로 써도 shinyApp 실행 가능.
  ui <- fluidPage(
     useShinyjs(),
     
     extendShinyjs(text = jsCode), # JS 함수를 Shiny(R)에서 사용이 가능하다는 뜻.
     selectInput("col", "Color", c("white", "red", "blue"))
  ),
  
  # Define server logic required to draw a histogram
  server <- function(input, output) {
     observeEvent(input$col, {
       js$pageCol(input$col) # JavaScript 함수를 호출. jscode 안에 pageCol에 파라미터 값을 줌.
     })
  }
)

