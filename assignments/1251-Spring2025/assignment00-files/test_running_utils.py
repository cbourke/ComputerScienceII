import unittest

from running_utils import elevation_gain
from running_utils import convert_map

class RunningUtilsUnitTests(unittest.TestCase):
    """
    Unit testing suite for the `running_utils` module.
    """

    TOLERANCE = 0.001

    def is_close(a, b, tolerance):
        """
        Utility method to determine if two values are close (within the
        specified `tolerance` in absolute value).  The arguments `a` and
        `b` can be either single numeric values or lists of numbers (which
        must be the same size) or list of lists of numbers.
        """
        if isinstance(a, (float, int)) and isinstance(b, (float, int)):
            return (abs(a-b) <= tolerance)
        elif isinstance(a, list) and isinstance(b, list):
            if len(a) != len(b): return False
            for i in range( len(a) ):
                if not isinstance(a[i], (float, int)) or not isinstance(b[i], (float, int)): return False
                if abs(a[i]-b[i]) > tolerance:
                    return False
            return True
        else:
            return False

    def is_close_2d(a, b, tolerance):
        """
        Utility method to determine if two 2-D arrays' values are
        close (within the specified `tolerance` in absolute value).
        """
        if isinstance(a, list) and isinstance(b, list):
            if len(a) != len(b): return False
            for i in range( len(a) ):
                if not RunningUtilsUnitTests.is_close(a[i], b[i], 0.001):
                    return False
            return True
        else:
            return False

    def test_elevation_gain_001(self):
        elevations = [350.0, 352.5, 351.2, 355.2, 354.0]
        expected = 6.5
        actual = elevation_gain(elevations)
        self.assertTrue( RunningUtilsUnitTests.is_close(expected, actual, self.TOLERANCE) )

    def test_elevation_gain_002(self):
        elevations = [1.0, 2.0, 3.0, 4.0, 5.0]
        expected = 4.0
        actual = elevation_gain(elevations)
        self.assertTrue( RunningUtilsUnitTests.is_close(expected, actual, self.TOLERANCE) )

    def test_elevation_gain_003(self):
        elevations = [5.0, 4.0, 3.0, 2.0, 1.0]
        expected = 0.0
        actual = elevation_gain(elevations)
        self.assertTrue( RunningUtilsUnitTests.is_close(expected, actual, self.TOLERANCE) )

    #TODO: add 2 more non-trivial test cases of your own

    def test_convert_map_001(self):
        map = [
          [ 9.50, 8.75, 7.25, 8.25, 8.25 ],
          [ 8.50, 9.35, 6.45, 6.50, 7.25 ],
          [ 7.50, 8.60, 4.50, 5.50, 5.75 ]
        ]
        expected = [
          [ 31.167, 28.707, 23.786, 27.067, 27.066 ],
          [ 27.887, 30.675, 21.161, 21.325, 23.786 ],
          [ 24.606, 28.215, 14.763, 18.044, 18.864 ]
        ]
        actual = convert_map(map)
        self.assertTrue( RunningUtilsUnitTests.is_close_2d(expected, actual, self.TOLERANCE) )

    def test_convert_map_002(self):
        map = [
          [ 1, 2 ],
          [ 3, 4 ],
          [ 5, 6 ]
        ]
        expected = [
          [ 3.28084, 6.56168 ],
          [ 9.84252, 13.12336 ],
          [ 16.4042, 19.68504 ]
        ]
        actual = convert_map(map)
        self.assertTrue( RunningUtilsUnitTests.is_close_2d(expected, actual, self.TOLERANCE) )

    def test_convert_map_003(self):
        map = [
          [ 2, 3, 5, 7, 11, 13 ],
        ]
        expected = [
          [ 6.56168, 9.84252, 16.4042, 22.96588, 36.08924, 42.65092 ],
        ]
        actual = convert_map(map)
        self.assertTrue( RunningUtilsUnitTests.is_close_2d(expected, actual, self.TOLERANCE) )

    #TODO: add 2 more non-trivial test cases of your own

if __name__ == '__main__':
    #buffer=True suppresses stdout
    unittest.main(buffer=True)
