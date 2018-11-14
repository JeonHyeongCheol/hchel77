'''
Created on 2018. 11. 14.

@author: kitcoop
'''
import wx

class Ex(wx.Frame):
    def __init__(self, parent, title): # 값을 받음.
        super(Ex, self).__init__(parent,title=title, size=(300,300)) # 받은 값들을 넣어주면 size 만큼 창 열어줌?
        
        #TextBox
        #self.txtA = wx.TextCtrl(self) # Text Editor 만듬.
        #self.txtA = wx.TextCtrl(self, style = wx.TE_MULTILINE) # 스크롤바, 멀티라인으로 생성.
        
        self.CreateStatusBar() # 상태 표시줄
        
        panel = wx.Panel(self) # 판넬 값 변수에 담아줌.
        wx.StaticText(panel, label = 'i d : ', pos = (5, 5)) # 판넬안에 라벨 넣어주기 
        wx.StaticText(panel, label = 'pwd : ', pos = (5, 40))
        self.txtA = wx.TextCtrl(panel, pos = (50,5))
        self.txtB = wx.TextCtrl(panel, pos = (50,40), size=(150, -1)) # -1은 System에서 알아서 결정 할 수 있도록 설정해주는 것.
        
        # 버튼 처리
        btn1 = wx.Button(panel, label="일반버튼", pos = (5,100))
        btn2 = wx.ToggleButton(panel, label="토글버튼", pos = (100,100))
        btn3 = wx.ToggleButton(panel, label="종료", pos = (200,100), size=(50, -1))
        
        # 이벤트 처리 1
        
#         btn1.Bind(wx.EVT_BUTTON, self.OnClick1) # 클릭하면 해당 메소드로감.
#         btn2.Bind(wx.EVT_TOGGLEBUTTON, self.OnClick2) # 토클(True, False로만 나타남.)버튼은 따로 메소드가 있음.
#         btn3.Bind(wx.EVT_BUTTON, self.OnClick3)
        
        # 이벤트 처리 2

        btn1.id = 1
        btn2.id = 2
        btn3.id = 3
        

        self.Center() # 화면상에 중앙에 창 띄움.
        self.Show() # 보여주기
        
        
    def Abc(self):
        print('일반 메소드')
        
    def OnClick1(self, event): # 이벤트 핸들러 메소드(event라고 받은 변수값에 써줘야 함).
        # 대화상자 호출
        msgDial = wx.MessageDialog(self, "메세지", "제목", wx.OK)
        msgDial.ShowModal()
        msgDial.Destroy()
        
        self.SetTitle("버튼 1 클릭")
        
    def OnClick2(self, event):
        #print(event.GetEventObject().GetValue())
        if(event.GetEventObject().GetValue()):
            self.txtA.SetLabelText('kbs')
            self.txtB.SetLabelText('9')
        else:
            self.txtA.SetLabelText('')
            self.txtB.SetLabelText('')
    def OnClick3(self, event):
        #pass
        self.close()
        
    def OnClickHandler(self, event):
        #print(event.getEventObject().id)
        if event.getEventObject().id == 1:
            self.txtA.SetLabelText("첫번째")
        elif event.getEventObject().id == 2:
            self.txtA.SetLabelText("와우")
        else:
            self.Abc()

if __name__ == '__main__':
    app = wx.App() # 변수에 wx.app 넣어줌.
    Ex(None, title='제목') # 값 넣어줌.
    app.MainLoop() # 실행.