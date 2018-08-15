from django.shortcuts import render
from django.http import HttpResponse
from .signals import *
def index(request):

    return HttpResponse("Hello, world. You're at the fooddelivery index.")
# Create your views here.
