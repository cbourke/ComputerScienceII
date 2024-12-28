"""
TODO: author/program documentation
"""
import datetime
import math
import pprint
import sys
import time

def seconds_between(a, b):
    """
    Computes and returns the difference in time in seconds between
    two given ISO8601 formatted strings `a` and `b`.
    """
    time_a = datetime.datetime.strptime(a, "%Y-%m-%dT%H:%M:%S")
    time_b = datetime.datetime.strptime(b, "%Y-%m-%dT%H:%M:%S")
    return (time_b-time_a).total_seconds()

def format_seconds(seconds):
    """
    Returns a formatted string of the given number of `seconds`
    into the format "HH:MM:SS"
    """
    return time.strftime('%H:%M:%S', time.gmtime(seconds))
