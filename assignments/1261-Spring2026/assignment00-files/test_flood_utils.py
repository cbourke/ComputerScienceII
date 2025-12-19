import unittest

from flood_utils import convert_flow
from flood_utils import flood_capacity

class FloodUtilsUnitTests(unittest.TestCase):
    """
    Unit testing suite for the `flood_utils` module.
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

    def test_convert_flow_001(self):
        elevations = [350, 352.5, 351.2, 355.2, 354.0]
        expected = [3767.3685, 3794.2782, 3780.2851, 3823.3408, 3810.4241]
        actual = convert_flow(elevations)
        self.assertTrue( FloodUtilsUnitTests.is_close(expected, actual, self.TOLERANCE),
                        f'Expected: {expected}, Actual: {actual}' )

    def test_convert_flow_002(self):
        elevations = [1, 2, 3, 4]
        expected = [10.7639, 21.5278, 32.2917, 43.0556]
        actual = convert_flow(elevations)
        self.assertTrue( FloodUtilsUnitTests.is_close(expected, actual, self.TOLERANCE),
                        f'Expected: {expected}, Actual: {actual}' )

    def test_convert_flow_003(self):
        elevations = []
        expected = []
        actual = convert_flow(elevations)
        self.assertTrue( FloodUtilsUnitTests.is_close(expected, actual, self.TOLERANCE),
                        f'Expected: {expected}, Actual: {actual}' )

    #TODO: add 2 more non-trivial test cases of your own

    def test_flood_capacity_001(self):
        elevations = [
          [ 9.50, 4.75, 7.25, 8.25, 8.25 ],
          [ 8.50, 9.35, 6.45, 6.50, 7.25 ],
          [ 7.50, 8.60, 4.50, 5.50, 5.75 ]
        ]
        water_level = 6.0
        expected = 3.5
        actual = flood_capacity(elevations, water_level)
        self.assertTrue( FloodUtilsUnitTests.is_close(expected, actual, self.TOLERANCE),
                        f'Expected: {expected}, Actual: {actual}'  )

    def test_flood_capacity_002(self):
        elevations = [
          [ 9.50 ],
          [ 8.50 ],
          [ 7.50 ]
        ]
        water_level = 6.0
        expected = 0
        actual = flood_capacity(elevations, water_level)
        self.assertTrue( FloodUtilsUnitTests.is_close(expected, actual, self.TOLERANCE),
                        f'Expected: {expected}, Actual: {actual}'  )

    def test_flood_capacity_003(self):
        elevations = [
          [ 9.50, 4.75, 7.25, 8.25, 8.25 ]
        ]
        water_level = 6.0
        expected = 1.25
        actual = flood_capacity(elevations, water_level)
        self.assertTrue( FloodUtilsUnitTests.is_close(expected, actual, self.TOLERANCE),
                        f'Expected: {expected}, Actual: {actual}'  )

    #TODO: add 2 more non-trivial test cases of your own

if __name__ == '__main__':
    #buffer=True suppresses stdout
    unittest.main(buffer=True)
