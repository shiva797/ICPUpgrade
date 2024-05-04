echo @off
echo "<<<<<<<<<<<<<<< Current time >>>>>>>>>>>>>" is %TIME% >E:\238\Log\Screening.txt
cd /d E:\Office\dcmtk-3.6.3-win64-dynamic\bin
echo Before modifying the Dicom Data >>E:\238\Log\Screening.txt
rem dcmdump E:\238\Screening >>Screening.txt
dcmdump E:\238\Screening\*3cb8cb.dcm >>E:\238\Log\Screening.txt
dcmodify -nb -gst -gse -gin "E:\238\Screening\*.dcm"
echo "<<<<<<<<<<<<<<< Current time >>>>>>>>>>>>>>" %TIME% >>E:\238\Log\Screening.txt
echo After modifying the Dicom Data >>E:\238\Log\Screening.txt
dcmdump E:\238\Screening\*3cb8cb.dcm >>E:\238\Log\Screening.txt