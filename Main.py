import json
import subprocess

def kredihesaplama():
    
    with open("data.json" , "r") as jsonfile:
        jsondata = json.load(jsonfile)

    kredi = jsondata["kredi"]
    vade = jsondata["vade"]
    faiz = jsondata["faiz"] 
    ad = jsondata["ad"]
    
    if faiz != 0:
        fark = kredi * (faiz / 100)
        toplam_odeme = kredi + fark
    else:
        toplam_odeme = kredi

    vadeay = toplam_odeme / vade
    
    
    ayformat = "{:,.0f}".format(vadeay)
    faizformat = "{:.0f}".format(faiz)
    krediformat = "{:,.0f}".format(kredi)
    toplamformat = "{:,.0f}".format(toplam_odeme)
    
    
    cikti = "Sayın " + ad + " " + str(krediformat) + " Tutarındaki " + str(vade) + " Ay Vadeli Kredinizin Geri Ödeme Tutarı " + str(toplamformat) + " dır. Bilginize."
    
    cikti = f"echo {cikti}"

    adcmd = f"echo Adınız: {ad}"
    tutarcmd = f"echo Kredi Tutarı: {krediformat} TL"
    vadecmd = f"echo Vade: {vade} ay"
    faizcmd = f"echo Faiz Oranı: %{faizformat}"
    aylikcmd = f"echo Aylık Ödeme Tutarı: {ayformat} TL"
    toplamcmd = f"echo Toplam Ödeme Tutarı: {toplamformat} TL"

    cikti = f"{cikti} && echo. && {adcmd} && {tutarcmd} && {vadecmd} && {faizcmd} && {aylikcmd} && {toplamcmd}"
    subprocess.run(['start', 'cmd', '/K', cikti], shell=True)


kredihesaplama()



