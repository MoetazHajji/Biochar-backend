import os
import sys
import requests
from bs4 import BeautifulSoup

url = os.environ.get("SCRAPING_URL")
if not url:
    print("No URL provided")
    sys.exit(1)

response = requests.get(url)
soup = BeautifulSoup(response.content, "html.parser")
title = soup.title.string

print(title)