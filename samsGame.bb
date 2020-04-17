; Maxwell's Demon
; -------------------

width=800
height=600
depth=16
mode=2

Graphics3D width,height,depth,mode
SetBuffer BackBuffer()

Type Player

	Field px#, py#, pz#

End Type

p.Player = New Player
p\px# = 0
p\py# = 10
p\pz# = -50


cam=CreateCamera()
PositionEntity cam,0,10,-50


light=CreateLight()
RotateEntity light,90,0,0





;create suspects

;left suspect
sus1=LoadMD2("media/gargoyle.md2")
ScaleEntity sus1,.2,.2,.2
PositionEntity sus1,-40,0,50
RotateEntity sus1, 0,180,0
EntityColor sus1, 153,153,153
c1 = 0


;middle suspect
sus2=LoadMD2("media/gargoyle.md2")
ScaleEntity sus2,.2,.2,.2
PositionEntity sus2,0,0,50
RotateEntity sus2, 0,180,0
EntityColor sus2, 0,53,0
c2 = 1

;right suspect
sus3=LoadMD2("media/gargoyle.md2")
ScaleEntity sus3,.2,.2,.2
PositionEntity sus3, 40,0,50
RotateEntity sus3, 0,180,0
EntityColor sus3, 105, 51, 51
c3 = 2


;create rooms

;left room
room1=CreateCube()
EntityColor room1,0,102,102
FitMesh room1,-60,-10,30,40,40,40
FlipMesh room1
;middle room
room2=CreateCube()
EntityColor room2,0,102,102
FitMesh room2,-20,-10,30,40,40,40
FlipMesh room2
;right room
room3=CreateCube()
EntityColor room3,0,102,102
FitMesh room3,20,-10,30,40,40,40
FlipMesh room3

;create desks


;left desk
desk1=LoadMesh("media/StylishDesk.3ds")
FitMesh desk1, -140, 0, 20, 200, 8, 50
FlipMesh desk1

;middle desk
desk2=LoadMesh("media/StylishDesk.3ds")
FitMesh desk2, -100, 0, 20, 200, 8, 50
FlipMesh desk2

;right desk
desk3=LoadMesh("media/StylishDesk.3ds")
FitMesh desk3, -60, 0, 20, 200, 8, 50
FlipMesh desk3


; Create some scenery

; ground
ground=CreatePlane()
;EntityColor ground,168,133,55
ground_tex=LoadTexture("media/blacklodge.jpg")
ScaleTexture ground_tex,10,10
EntityTexture ground,ground_tex

; sky
sky=CreateSphere(24)
ScaleEntity sky,500,500,500
EntityColor sky, 155,0,0
FlipMesh sky
EntityFX sky,1
sky_tex=LoadTexture("media/sky.bmp")
EntityTexture sky,sky_tex

heaven=CreatePlane()

h_tex=LoadTexture("media/Moss.bmp")

ScaleTexture h_tex, 10, 10
EntityTexture heaven, h_tex
PositionEntity heaven, 0, 20000, 0

sky2=CreateSphere(24)
ScaleEntity sky2,500,500,500
FlipMesh sky2
EntityFX sky2,1
;sky_tex=LoadTexture("media/sky.bmp")
EntityTexture sky2,sky_tex

PositionEntity sky2, 0, 20000, 0


Dim clue$(2)


clue$(0) = "Nice day out"

clue$(1) = "A liar keeps you trapped"

clue$(2) = "We Winged Demons Three"

SeedRnd(MilliSecs()) 
t = Rnd(2)

If t = 1

	c1 = 1

	c2 = 2

	c3 = 0

Else If t = 2

	c1= 2

	c2 = 0

	c3 = 1

EndIf 

correct = 0


While Not KeyDown(1)

	; Control camera
	
	; mouse look
	
	;mxs#=(mxs#+(MouseXSpeed()/5.0)) Mod 360
	;mys#=mys#+(MouseYSpeed()/5.0)

	;RotateEntity cam,mys#,-mxs#,0

	;MoveMouse width/2,height/2

	

	 ;move camera forwards/backwards/Left/Right with cursor keys
	
	If KeyDown(200)=True And p\pz# < 30 Then p\pz# = p\pz# + .3 ;MoveEntity cam,0,0,.3 ; move camera forward
	If KeyDown(208)=True And p\pz# > -50  Then p\pz# = p\pz# - .3;MoveEntity cam,0,0,-.3 ; move camera back

	If KeyDown(205)=True And p\px# < 60 Then p\px# = p\px# + .3 ;MoveEntity cam,.3,0,0 ; move camera left
	If KeyDown(203)=True And p\px# > -60 Then p\px# = p\px# - .3 ;MoveEntity cam,-.3,0,0 ; move camera right
	
	PositionEntity cam,p\px#,p\py#,p\pz#
	
	UpdateWorld
	RenderWorld
	
	Text 0,0,t + "Maxwell's Demon"+ p\px#
	Text 0,20,correct + "Move with arrow keys. Accuse by pressing space in a room."+p\pz#

	If correct = 1 Then Text 380, 100, "You got him. Welcome to Paradise."
	If correct = 2 
		Text 380, 100, "You failed. Fall to the Abyss." 
		p\px# = 0 
		p\py# = p\py# - 1 
		p\pz# = 0
		RotateEntity cam, -90, 0, 0

	EndIf 

	If p\pz# > 20 And correct = 0
		
		If p\px# > -60 And p\px# < -20 Then Text 380, 200, clue$(c1)

		If p\px# > -20 And p\px# < 20 Then Text 380, 200, clue$(c2)

		If p\px# > 20 And p\px# < 60 Then Text 380, 200, clue$(c3)

	EndIf 

	If KeyDown(57) And p\pz# > 20 And correct = 0

		If p\px# > -60 And p\px# < -20 And c1 = 0

			correct = 1

			p\py# = 20010

		Else If p\px# > -20 And p\px# < 20 And c2 = 0

			correct = 1

			p\py# = 20010

		Else If p\px# > 20 And p\px# < 60 And c3 = 0

			correct = 1

			p\py# = 20010

		Else 

			correct = 2

		EndIf 

	EndIf 

	Flip

Wend

End
;~IDEal Editor Parameters:
;~C#Blitz3D
