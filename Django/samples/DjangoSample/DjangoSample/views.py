from django.http import HttpResponse
from django.shortcuts import render

__author__ = 'canbin.zhang'


def hello(request):
    return HttpResponse('<p> hello world! </p>')

#def hello(request):
#    context = {}
#    context['hello'] = 'Hello world!(render)'
#    return render(request, 'hello.html', context)