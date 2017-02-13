@echo off
cls
echo "Programing£¬Please Wait..."
c:
cd C:\Program Files\pgo\USBDM 4.12.1.80\
usbdmflashprogrammer -target=hcs08 Image.s19 -device=MC9S08AW16A -trim=243 -program -secure
echo errorlevel = %ERRORLEVEL%
echo "DONE!"