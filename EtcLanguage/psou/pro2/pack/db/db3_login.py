'''
Created on 2018. 11. 15.

# wxFormBuilder
wxListCrtl 에서 style에서 ICON 체크 해제 후 REPORT 체크 해야지 나중에 List가 나옴.
'''
# -*- coding: utf-8 -*- 

###########################################################################
## Python code generated with wxFormBuilder (version Jun 17 2015)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################
###########################################################################
## Class MyForm
###########################################################################

import wx
import wx.xrc

import MySQLdb
import sys

config = { # dic 타입!
    'host':'127.0.0.1',
    'user':'root',
    'password':'123',
    'database':'test',
    'port':3306,
    'charset':'utf8',
    'use_unicode':True
}

class MyForm ( wx.Frame ):
    
    def __init__( self, parent ):
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = u"로그인 후 처리", pos = wx.DefaultPosition, size = wx.Size( 400,326 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
        
        #self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_INFOBK ) )
        
        bSizer1 = wx.BoxSizer( wx.VERTICAL )
        
        self.m_panel1 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer5 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.m_staticText3 = wx.StaticText( self.m_panel1, wx.ID_ANY, u"사번 : ", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText3.Wrap( -1 )
        bSizer5.Add( self.m_staticText3, 0, wx.ALL, 5 )
        
        self.txtBun = wx.TextCtrl( self.m_panel1, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 60,-1 ), 0 )
        bSizer5.Add( self.txtBun, 0, wx.ALL, 5 )
        
        self.m_staticText4 = wx.StaticText( self.m_panel1, wx.ID_ANY, u"직원명 : ", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText4.Wrap( -1 )
        bSizer5.Add( self.m_staticText4, 0, wx.ALL, 5 )
        
        self.txtName = wx.TextCtrl( self.m_panel1, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.Size( 100,-1 ), 0 )
        bSizer5.Add( self.txtName, 0, wx.ALL, 5 )
        
        self.btnOk = wx.Button( self.m_panel1, wx.ID_ANY, u"로그인", wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer5.Add( self.btnOk, 0, wx.ALL, 5 )
        
        
        self.m_panel1.SetSizer( bSizer5 )
        self.m_panel1.Layout()
        bSizer5.Fit( self.m_panel1 )
        bSizer1.Add( self.m_panel1, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel2 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer6 = wx.BoxSizer( wx.VERTICAL )
        
        self.staResult = wx.StaticText( self.m_panel2, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        self.staResult.Wrap( -1 )
        bSizer6.Add( self.staResult, 0, wx.ALL, 5 )
        
        
        self.m_panel2.SetSizer( bSizer6 )
        self.m_panel2.Layout()
        bSizer6.Fit( self.m_panel2 )
        bSizer1.Add( self.m_panel2, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel3 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer7 = wx.BoxSizer( wx.VERTICAL )
        
        self.lstView = wx.ListCtrl( self.m_panel3, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.LC_REPORT )
        bSizer7.Add( self.lstView, 1, wx.ALL|wx.EXPAND, 5 )
        
        
        self.m_panel3.SetSizer( bSizer7 )
        self.m_panel3.Layout()
        bSizer7.Fit( self.m_panel3 )
        bSizer1.Add( self.m_panel3, 1, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel4 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer8 = wx.BoxSizer( wx.VERTICAL )
        
        self.staCount = wx.StaticText( self.m_panel4, wx.ID_ANY, u"인원 수 : 0명", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.staCount.Wrap( -1 )
        bSizer8.Add( self.staCount, 0, wx.ALL, 5 )
        
        
        self.m_panel4.SetSizer( bSizer8 )
        self.m_panel4.Layout()
        bSizer8.Fit( self.m_panel4 )
        bSizer1.Add( self.m_panel4, 0, wx.EXPAND |wx.ALL, 5 )
        
        
        self.SetSizer( bSizer1 )
        self.Layout()
        
        self.Centre( wx.BOTH )
        
        # Connect Events
        self.btnOk.Bind( wx.EVT_BUTTON, self.OnButtonClick )
    
        # lstView에 표제
        self.lstView.InsertColumn(0, '고객번호', width=100)
        self.lstView.InsertColumn(1, '고객명')
        self.lstView.InsertColumn(2, '고객전화')
        
    def __del__( self ):
        pass
    
    def OnButtonClick(self, event):
        if self.txtBun.GetValue() == "":
            wx.MessageBox("직원번호 입력", "알림", wx.OK)
            self.txtBun.SetFocus()
            return
        
        if self.txtName.GetValue() == "":
            wx.MessageBox("직원명 입력", "알림", wx.OK)
            self.txtName.SetFocus()
            return
    
        self.LoginCheck()
        
    def LoginCheck(self):
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            
            jikwon_no = self.txtBun.GetValue()
            jikwon_name = self.txtName.GetValue()
            
            sql = """
            select count(*) 
            from jikwon
            where jikwon_no = {0} and jikwon_name = '{1}'
            """.format(jikwon_no, jikwon_name) # {0} : jikwon_no, {1} : jikwon_name.
            
            cursor.execute(sql)
            count = cursor.fetchone()[0]

            if count == 0:
                wx.MessageBox("로그인 실패", "알림", wx.OK)
                self.txtBun.SetLabelText("")
                self.txtName.SetLabelText("")
                self.staCount.SetLabel("인원 수 : 0명")
                self.staResult.SetLabel("")
                self.lstView.DeleteAllItems()
            else :
                self.staResult.SetLabelText(jikwon_no + "번 직원의 관리 고객 목록")
                self.ShowGogek(jikwon_no)
                
        except Exception as e:
            print("Login Check Err : ", e)
        
        finally:
            cursor.close()
            conn.close()
        
    def ShowGogek(self, jikwon_no):
        #print(jikwon_no)
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            
            sql = """
            select gogek_no, gogek_name, gogek_tel
            from gogek
            where gogek_damsano={}
            """.format(jikwon_no)
        
            cursor.execute(sql)
            gogekDatas = cursor.fetchall()
        
            self.lstView.DeleteAllItems() # 표를 초기화
            
            for d in gogekDatas:
                i = self.lstView.InsertItem(65000,0) # 행에 대한 번호, 65000은 몇개까지 읽을지...!!
                self.lstView.SetItem(i, 0, str(d[0])) # 행에 대한 칼럼 값들
                self.lstView.SetItem(i, 1, d[1])
                self.lstView.SetItem(i, 2, d[2])
                
            self.staCount.SetLabelText("인원 수 : " + str(len(gogekDatas)) + "명")
        
        except Exception as e:
            print("Show Gogek Err : ", e)
            
        finally:
            cursor.close()
            conn.close()
        
if __name__ == '__main__':
    app = wx.App() 
    MyForm(None).Show()
    app.MainLoop()