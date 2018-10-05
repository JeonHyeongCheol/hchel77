library(shiny)

# Define UI for application that draws a histogram
ui <- fluidPage(
   
   # Application title
   br(),
   textOutput("currentTime"),
   br(),
   actionButton("btnDo", "실행"),
   plotOutput("histo"),
   hr(),
   column(3, numericInput('cou', '출력 행수', 5)), # 3은 너비 조정.
   br(),
   actionButton("btnClick", "클릭"),
   column(8, tableOutput('table'))
)

# Define server logic required to draw a histogram
server <- function(input, output) {
  timer <- reactiveTimer(1000, session = getDefaultReactiveDomain()) # 지연시간, 1000은 1초
  output$currentTime <- renderText({
    timer()
    paste('현재 시간은 ', Sys.time())
  })
  
  # eventReactive() 이벤트 처리 : 반응성 표현식을 반환(값이 필요할 때 사용)
  func1 <- eventReactive(input$btnDo, { # 버튼이 누르게 되면 아래라인 코드 실행.
    rnorm(1000) # 난수값을 써서 랜덤으로 값이 바뀌게 하고 있음. 1초에 한번씩.
  })
  
  output$histo <- renderPlot({
    hist(func1()) # 결국 실행시 다 다른 값이 나오게 됨.
  })
  
  # observeEvent() : 반응성 관찰자를 반환, 어떠한 액션이 필요한 경우
  observeEvent(input$btnClick, { # 버튼 클릭시 밑에 라인 실행.
     cat('showing', input$cou, 'rows\n') # 클릭시 cou 값 가져와 출력.
  })
  
  func2 <- eventReactive(input$btnClick, {
    # name <- c('tom', 'james', 'sujan')
    # gender <- c('M', 'M', 'F')
    # age <- c(22, 33, 44)
    # datas <- data.frame(name, gender, age)
    # head(datas, input$cou) # cou 값만큼 datas 출력
    
    head(cars, input$cou)
  })
  
  output$table <- renderTable({
    func2()
  })
}

# Run the application 
shinyApp(ui = ui, server = server)

