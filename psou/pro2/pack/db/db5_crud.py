'''
Created on 2018. 11. 15.

remote DB (CRUD)
'''

# -*- coding: utf-8 -*- 

###########################################################################
## Python code generated with wxFormBuilder (version Jun 17 2015)
## http://www.wxformbuilder.org/
##
## PLEASE DO "NOT" EDIT THIS FILE!
###########################################################################

import wx
import wx.xrc

###########################################################################
## Class MyFrame1
###########################################################################

import MySQLdb
from astropy.units import count

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
        wx.Frame.__init__ ( self, parent, id = wx.ID_ANY, title = wx.EmptyString, pos = wx.DefaultPosition, size = wx.Size( 305,392 ), style = wx.DEFAULT_FRAME_STYLE|wx.TAB_TRAVERSAL )
        
        #self.SetSizeHintsSz( wx.DefaultSize, wx.DefaultSize )
        self.SetBackgroundColour( wx.SystemSettings.GetColour( wx.SYS_COLOUR_INFOBK ) )
        
        bSizer1 = wx.BoxSizer( wx.VERTICAL )
        
        self.m_panel1 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer4 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.m_staticText1 = wx.StaticText( self.m_panel1, wx.ID_ANY, u"번호 :", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText1.Wrap( -1 )
        bSizer4.Add( self.m_staticText1, 0, wx.ALL, 5 )
        
        self.txtNo = wx.TextCtrl( self.m_panel1, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer4.Add( self.txtNo, 0, wx.ALL, 5 )
        
        self.btnInsert = wx.Button( self.m_panel1, wx.ID_ANY, u"등록", wx.DefaultPosition, wx.Size( 110,-1 ), 0 )
        bSizer4.Add( self.btnInsert, 0, wx.ALL, 5 )
        
        
        self.m_panel1.SetSizer( bSizer4 )
        self.m_panel1.Layout()
        bSizer4.Fit( self.m_panel1 )
        bSizer1.Add( self.m_panel1, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel2 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer5 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.m_staticText3 = wx.StaticText( self.m_panel2, wx.ID_ANY, u"이름 :", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText3.Wrap( -1 )
        bSizer5.Add( self.m_staticText3, 0, wx.ALL, 5 )
        
        self.txtName = wx.TextCtrl( self.m_panel2, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer5.Add( self.txtName, 0, wx.ALL, 5 )
        
        self.btnUpdate = wx.Button( self.m_panel2, wx.ID_ANY, u"수정", wx.DefaultPosition, wx.Size( 50,-1 ), 0 )
        bSizer5.Add( self.btnUpdate, 0, wx.ALL, 5 )
        
        self.btnConfirm = wx.Button( self.m_panel2, wx.ID_ANY, u"확인", wx.DefaultPosition, wx.Size( 50,-1 ), 0 )
        bSizer5.Add( self.btnConfirm, 0, wx.ALL, 5 )
        
        
        self.m_panel2.SetSizer( bSizer5 )
        self.m_panel2.Layout()
        bSizer5.Fit( self.m_panel2 )
        bSizer1.Add( self.m_panel2, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel3 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer6 = wx.BoxSizer( wx.HORIZONTAL )
        
        self.m_staticText4 = wx.StaticText( self.m_panel3, wx.ID_ANY, u"전화 :", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.m_staticText4.Wrap( -1 )
        bSizer6.Add( self.m_staticText4, 0, wx.ALL, 5 )
        
        self.txtTel = wx.TextCtrl( self.m_panel3, wx.ID_ANY, wx.EmptyString, wx.DefaultPosition, wx.DefaultSize, 0 )
        bSizer6.Add( self.txtTel, 0, wx.ALL, 5 )
        
        self.btnDel = wx.Button( self.m_panel3, wx.ID_ANY, u"삭제", wx.DefaultPosition, wx.Size( 110,-1 ), 0 )
        bSizer6.Add( self.btnDel, 0, wx.ALL, 5 )
        
        
        self.m_panel3.SetSizer( bSizer6 )
        self.m_panel3.Layout()
        bSizer6.Fit( self.m_panel3 )
        bSizer1.Add( self.m_panel3, 0, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel4 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer7 = wx.BoxSizer( wx.VERTICAL )
        
        self.lstMem = wx.ListCtrl( self.m_panel4, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.LC_REPORT )
        bSizer7.Add( self.lstMem, 1, wx.ALL, 5 )
        
        
        self.m_panel4.SetSizer( bSizer7 )
        self.m_panel4.Layout()
        bSizer7.Fit( self.m_panel4 )
        bSizer1.Add( self.m_panel4, 1, wx.EXPAND |wx.ALL, 5 )
        
        self.m_panel5 = wx.Panel( self, wx.ID_ANY, wx.DefaultPosition, wx.DefaultSize, wx.TAB_TRAVERSAL )
        bSizer8 = wx.BoxSizer( wx.VERTICAL )
        
        self.staCnt = wx.StaticText( self.m_panel5, wx.ID_ANY, u"0", wx.DefaultPosition, wx.DefaultSize, 0 )
        self.staCnt.Wrap( -1 )
        bSizer8.Add( self.staCnt, 0, wx.ALL, 5 )
        
        
        self.m_panel5.SetSizer( bSizer8 )
        self.m_panel5.Layout()
        bSizer8.Fit( self.m_panel5 )
        bSizer1.Add( self.m_panel5, 0, wx.EXPAND |wx.ALL, 5 )
        
        
        self.SetSizer( bSizer1 )
        self.Layout()
        
        self.Centre( wx.BOTH )
        
        # ListMem
        self.lstMem.InsertColumn(0, '번호', width = 50)
        self.lstMem.InsertColumn(1, '이름')
        self.lstMem.InsertColumn(2, '전화')
        
        # Connect Events
        self.btnInsert.id = 1
        self.btnUpdate.id = 2
        self.btnConfirm.id = 3
        self.btnDel.id = 4
        self.btnConfirm.Enable(enable=False) # 수정의 확인은 비활성화
        
        self.btnInsert.Bind( wx.EVT_BUTTON, self.OnBtnGo )
        self.btnUpdate.Bind( wx.EVT_BUTTON, self.OnBtnGo )
        self.btnConfirm.Bind( wx.EVT_BUTTON, self.OnBtnGo )
        self.btnDel.Bind( wx.EVT_BUTTON, self.OnBtnGo )
    
        self.ViewList()
    
    def __del__( self ):
        pass
    
    def ViewList(self):
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            
            sql = "select * from pymem"
            cursor.execute(sql)
            self.lstMem.DeleteAllItems()
            count = 0
            for data in cursor:
                i = self.lstMem.InsertItem(10000, 0)
                self.lstMem.SetItem(i, 0, str(data[0]))
                self.lstMem.SetItem(i, 1, data[1])
                self.lstMem.SetItem(i, 2, data[2])
                count += 1
            self.staCnt.SetLabel("인원 수 : " + str(count))
                                              
        except Exception as e:
            print("ViewList Err : ", e)
        
        finally:
            cursor.close()
            conn.close()
    
    # Virtual event handlers, overide them in your derived class
    def OnBtnGo( self, event ):
        id = event.GetEventObject().id
        #print(id)
        if id == 1:
            self.MemInsert() # 추가.
        elif id == 2:
            self.MemUpdate() # 수정 준비
        elif id == 3:
            self.MemUpdateOk() # 수정 처리
        elif id == 4:
            self.MemDelete() # 삭제
        elif id == 5:
            self.MemUpdateCancel() # 수정 취소
            
            
    def MemInsert(self):
        no = self.txtNo.GetValue()
        name = self.txtName.GetValue()
        tel = self.txtTel.GetValue()
        
        if no == '' or name == '' or tel == '':
            wx.MessageBox("자료를 입력하시오", "경고", wx.OK)
            return
    
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            data = self.SelectData(no)
            if data != None: # 자료값이 있으면 사용중인 것. 자료값이 없으면 사용 가능.
                wx.MessageBox("이미 사용중인 번호 입니다.", "경고", wx.OK)
                self.txtNo.SetFocus()
                return
            
            # 등록작업 계속
            sql = "insert into pymem values(%s, %s, %s)"
            cursor.execute(sql, (no, name, tel))
            
            conn.commit()
            
            self.ViewList() # 추가 후 자료보기
            self.txtNo.SetValue('')
            self.txtName.SetValue('')
            self.txtTel.SetValue('')
        except Exception as e:
            wx.MessageBox("MemInsert Err : " + str(e), "경고", wx.OK)
            conn.rollback()
            
        finally:
            cursor.close()
            conn.close()
        
    def MemUpdate(self):
        pass
    
    def MemUpdateOk(self):
        pass
    
    def MemDelete(self):
        pass
    
    def MemUpdateCancel(self):
        pass
    
    def SelectData(self, no): # 해당 번호의 자료 읽기(추가, 수정, 삭제시 사용)
        try:
            conn = MySQLdb.connect(**config)
            cursor = conn.cursor()
            
            sql = "select * from pymem where bun = {}".format(no)
            cursor.execute(sql)
            data = cursor.fetchone()
            return data
            
        except Exception as e:
            wx.MessageBox("SelectData Err : " + str(e), "경고", wx.OK)
            conn.rollback()
            
        finally:
            cursor.close()
            conn.close()
    
if __name__ == '__main__':
    app = wx.App() 
    MyForm(None).Show()
    app.MainLoop()